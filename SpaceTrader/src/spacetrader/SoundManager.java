/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class SoundManager {
    public static final String CLICKID = "Click";
    public static final String CLICKPATH = "resources/StepFast-2.wav";
    public static final String STARTSCREENID = "OpenInitial";
    public static final String STARTSCREENPATH = "resources/OpenInitial.wav";
    
    private static final SoundManager soundManager = new SoundManager();
    
    private HashMap<String,AudioClip> soundEffectsMap = new HashMap();
    private HashMap<String,AudioClip> backgroundMusicMap = new HashMap();
    
    private ExecutorService soundPool = Executors.newFixedThreadPool(6);
    private double volumeBG = 1;
    private double volumeSE = 1;
    private boolean bgMuted = false;
    private boolean seMuted = false;
    private String previousScreen;
    
    private void loadSoundEffect(String id, String path){
        URL url = getClass().getResource(path);
        AudioClip soundEffect = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, soundEffect);
    }
    
    private void loadBackgroundMusic(String id, String path){
        URL url = getClass().getResource(path);
        AudioClip backgroundMusic = new AudioClip(url.toExternalForm());
        backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
        backgroundMusicMap.put(id, backgroundMusic);    
    }
    
    /**
     * 
     * @param id 
     */
    public void playSoundEffect(String id){
        if(!seMuted){
            Runnable sePlay = new Runnable(){
            @Override
              public void run(){
                soundEffectsMap.get(id).play(volumeSE);
              }
            };
            soundPool.execute(sePlay);
        } 
    }
    
    
    private void playBackgroundMusic(String id){
        Runnable bgPlay = new Runnable(){
            @Override
            public void run(){
                backgroundMusicMap.get(id).play(volumeBG);
            }
        };
        soundPool.execute(bgPlay);
    }
    
    /**
     * Plays the track given by id and path if the background music is not muted.
     * If the desired track is not loaded, it loads the music.
     * Checks for other playing tracks and if a different track is playing, stops it and plays the desired music.
     * @param id of the desired music to play
     * @param path of the desired music
     */
    public void playBGWithCheck(String id, String path){
        if(!bgMuted){
            //checks if the desired music is loaded or not, and loads it if needed
            if(soundManager.backgroundMusicMap.get(id) == null){
                soundManager.loadBackgroundMusic(id, path);
            }
            
            //if the current music is different from the desired track, stop current music
            String currentID = soundManager.currentlyPlayingBGMusicID();
            if(!currentID.equals(id) && !currentID.equals("")){
               soundManager.backgroundMusicMap.get(currentID).stop();
               soundManager.playBackgroundMusic(id);
            } else if (currentID.equals(id)){
                //correct music playing, do nothing
            } else {
                soundManager.playBackgroundMusic(id);
            }
        }
    }
    
    /**
     * 
     * @param id
     * @param path 
     */
    public void playSEWithCheck(String id, String path){
        if(!seMuted){
            //check if wanted sound effect is loaded
            if(soundManager.soundEffectsMap.get(id) == null){
                soundManager.loadSoundEffect(id, path);
            }
            soundManager.playSoundEffect(id);
        }
    }
    
    public String currentlyPlayingBGMusicID(){
        String id = "";
        Set<String> keys = soundManager.backgroundMusicMap.keySet();
        //if keys exist
        if(keys != null){
            for(String key : keys){
                if(soundManager.backgroundMusicMap.get(key).isPlaying()){
                    id = key;
                }
            }
        }
        return id;
    }
    
    public AudioClip getBackgroundMusic(String id){
        return backgroundMusicMap.get(id);
    }
    
    public boolean getBGMuted(){
        return bgMuted;
    }
    
    public boolean getSEMuted(){
        return seMuted;
    }
    
    private AudioClip getPlayingBGMusic(){
        AudioClip playing = null;
        backgroundMusicMap.values();
        
        
        return playing;
    }
    
    public void muteBackgroundMusic(String id){
        AudioClip toMute = soundManager.getBackgroundMusic(id);
        toMute.stop();
        bgMuted = true;
    }
    
    public void muteSoundEffects(){
        seMuted = true;
    }
    
    public void unMuteBackgroundMusic(String id){
        playBackgroundMusic(id);
        bgMuted = false;
    }
    
    public void unMuteSoundEffects(){
        seMuted = false;
    }
      
    public void setVolumeBG(double volume){
        volumeBG = volume;
        if (volume==0){
            bgMuted = true;
        }
    }
    
    public void setVolumeSE(double volume){
        volumeSE = volume;
    }
    
    public void setAllVolume(double bgVolume, double seVolume){
        setVolumeBG(bgVolume);
        setVolumeSE(seVolume);
    }
    
    public void setPrevScreen(String name){
        previousScreen = name;
    }
    
    public String getPrevScreen(){
        return previousScreen;
    }
    
    /**
     * stop all threads and media players
     */
    public void shutdown(){
        soundPool.shutdown();
    } 
    
    /**
     * Gets sound manager for the current game
     * @return the sound manager
     */
    public static SoundManager getSoundManager(){
        return soundManager;
    }
    
}
