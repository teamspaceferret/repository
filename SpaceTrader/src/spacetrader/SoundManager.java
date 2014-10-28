/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;



public class SoundManager implements Serializable{
    //private HashMap<String,AudioClip> soundEffectsMap = new HashMap();
    //private HashMap<String,AudioClip> backgroundMusicMap = new HashMap();
    private HashMap<String,AudioInputStream> backgroundMusicMap = new HashMap();
    
    
    private byte[] buf;
    
   /* public void SoundSerializer() throws FileNotFoundException, IOException{
        File file = new File("C:\\soundFile.wav");
        
        FileInputStream fis = new FileInputStream(file);
    }*/
    
    private ExecutorService soundPool;
    private double volumeBG;
    private double volumeSE;
    private boolean bgMuted;
    private boolean seMuted;
    
    public SoundManager(){
        soundPool = Executors.newFixedThreadPool(6);
        volumeBG = 1;
        volumeSE = 1;
        bgMuted = false;
        seMuted = false;
    }
    
    public SoundManager(int threadNumber){
        soundPool = Executors.newFixedThreadPool(threadNumber);
        volumeBG = 1;
        volumeSE = 1;
        bgMuted = false;
        seMuted = false;
    }
    
    /*
    public void loadSoundEffect(String id, String path){
        URL url = getClass().getResource(path);
        AudioClip soundEffect = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, soundEffect);
    }
    */
    
    /**
     * 
     * @param id
     * @param path
     */
    public void loadBackgroundMusic(String id, String path){
            
            
            URL url = getClass().getResource(path);
            
            /* 
            AudioClip backgroundMusic = new AudioClip(url.toExternalForm());
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
            backgroundMusicMap.put(id, backgroundMusic);
            return true;
            */
            
           // AudioStream stream;
           /*
            try {
                
                AudioStream stream = new AudioStream(url.openStream());
                AudioData data = stream.getData();
                ContinuousAudioDataStream cas = new ContinuousAudioDataStream(data);
                backgroundMusicMap.put(id, cas);
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
               return false;
            }
            */
            
            /*
             try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
                backgroundMusicMap.put(id, inputStream);
                return true;
             } catch (Exception ex) {
                 ex.printStackTrace();
                return false; 
             }
             */
           
            
            
           
        
    }
    
    /*
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
    */
    
    public void playBackgroundMusic(String id){
        Runnable bgPlay = new Runnable(){
            @Override
            public void run(){
                //backgroundMusicMap.get(id).play(volumeBG);
                //AudioClip clip = new AudioClip();
            }
        };
        soundPool.execute(bgPlay);
    }
   /* 
    public void playBGWithCheck(String id, String path){
        if(!bgMuted){
          if (this.getBackgroundMusic(id) != null){
            if (this.getBackgroundMusic(id).isPlaying()){ } 
            else {
              this.playBackgroundMusic(id);
            }
          } else {
            this.loadBackgroundMusic(id, path);
            this.playBackgroundMusic(id);
          }
        }
         
    }
    */
    
    /*public AudioClip getBackgroundMusic(String id){
        return backgroundMusicMap.get(id);
    }
    */
    
    public boolean getBGMuted(){
        return bgMuted;
    }
    
    public boolean getSEMuted(){
        return seMuted;
    }
    
    /*
    public AudioClip getPlayingBGMusic(){
        AudioClip playing = null;
        backgroundMusicMap.values();
        
        
        return playing;
    }
    */
    /*
    public void muteBackgroundMusic(String id){
        AudioClip toMute = getBackgroundMusic(id);
        toMute.stop();
        bgMuted = true;
    }
    */
    
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
    
    /*
    public void setVolumeBG(double volume){
        volumeBG = volume;
        if (volume==0){
            muteBG();
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
    */
    
    /**
     * stop all threads and media players
     */
    public void shutdown(){
        soundPool.shutdown();
    } 
}
