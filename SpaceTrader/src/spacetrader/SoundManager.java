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
    private static final SoundManager soundManager = new SoundManager();
    
    private HashMap<String,AudioClip> soundEffectsMap = new HashMap();
    private HashMap<String,AudioClip> backgroundMusicMap = new HashMap();
    
    private ExecutorService soundPool = Executors.newFixedThreadPool(6);
    private double volumeBG = 1;
    private double volumeSE = 1;
    private boolean bgMuted = false;
    private boolean seMuted = false;
    
    /**
     * 
     * @param id
     * @param path 
     */
    public void loadSoundEffect(String id, String path){
        URL url = getClass().getResource(path);
        AudioClip soundEffect = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, soundEffect);
    }
    
    /**
     * 
     * @param id
     * @param path
     */
    public void loadBackgroundMusic(String id, String path){
        URL url = getClass().getResource(path);
        AudioClip backgroundMusic = new AudioClip(url.toExternalForm());
        backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
        backgroundMusicMap.put(id, backgroundMusic);    
    }
    
    
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
    
    
    public void playBackgroundMusic(String id){
        Runnable bgPlay = new Runnable(){
            @Override
            public void run(){
                backgroundMusicMap.get(id).play(volumeBG);
            }
        };
        soundPool.execute(bgPlay);
    }
    
    public void playBackgroundMusic(String id, double volume){
        Runnable bgPlay = new Runnable(){
            @Override
            public void run(){
                backgroundMusicMap.get(id).play(volume);
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
    public void playBGWithCheck2(String id, String path){
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
    
    public void playBGWithCheck2(String id, String path, double volume){
        if(!bgMuted){
            //checks if the desired music is loaded or not, and loads it if needed
            if(soundManager.backgroundMusicMap.get(id) == null){
                soundManager.loadBackgroundMusic(id, path);
            }
            
            //if the current music is different from the desired track, stop current music
            String currentID = soundManager.currentlyPlayingBGMusicID();
            if(!currentID.equals(id) && !currentID.equals("")){
               soundManager.backgroundMusicMap.get(currentID).stop();
               soundManager.playBackgroundMusic(id, volume);
            } else if (currentID.equals(id)){
                //correct music playing, do nothing
            } else {
                soundManager.playBackgroundMusic(id, volume);
            }
          
        }
    }
    
    public void playBGWithCheck(String id, String path){
        if(!bgMuted){
          if (soundManager.getBackgroundMusic(id) != null){
            if (soundManager.getBackgroundMusic(id).isPlaying()){ } 
            else {
              soundManager.playBackgroundMusic(id);
            }
          } else {
            soundManager.loadBackgroundMusic(id, path);
            soundManager.playBackgroundMusic(id);
          }
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
    
    
    public AudioClip getPlayingBGMusic(){
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
    /*
    public void muteAllSound(String id){
        muteBackgroundMusic(id);
        muteSoundEffects();
    }
    */
    
    public void unMuteBackgroundMusic(String id){
        playBackgroundMusic(id);
        bgMuted = false;
    }
    
    public void unMuteSoundEffects(){
        seMuted = false;
    }
    
    public void unMuteAllSound(String id){
        unMuteBackgroundMusic(id);
        unMuteSoundEffects();
    }
    
    
    public void setVolumeBG(double volume){
        volumeBG = volume;
        if (volume==0){
            
        } else {
            
        }
        //loop through all music
        //bgmusic.stop
        //bgmusic.play
        //replay with current volume
    }
    
    public void setVolumeSE(double volume){
        volumeSE = volume;
        
    }
    
    public void setAllVolume(double volume){
        setVolumeBG(volume);
        setVolumeSE(volume);
    }
    
    
    /**
     * stop all threads and media players
     */
    public void shutdown(){
        soundPool.shutdown();
    } 
    
    public static SoundManager getSoundManager(){
        return soundManager;
    }
}
