/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.MalformedURLException;
import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SoundManager {
    private HashMap<String,AudioClip> soundEffectsMap = new HashMap();
    private HashMap<String,AudioClip> backgroundMusicMap = new HashMap();
    
    private ExecutorService soundPool;
    private double volumeBG;
    private double volumeSE;
    
    public SoundManager(){
        soundPool = Executors.newFixedThreadPool(6);
        volumeBG = 1;
        volumeSE = 1;
    }
    
    public SoundManager(int threadNumber){
        soundPool = Executors.newFixedThreadPool(threadNumber);
        volumeBG = 1;
        volumeSE = 1;
    }
    
    public void loadSoundEffect(String id, String path){
        URL url = getClass().getResource(path);
        AudioClip soundEffect = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, soundEffect);
    }
    
    public void loadBackgroundMusic(String id, String path){
        
            URL url = getClass().getResource(path);
            AudioClip backgroundMusic = new AudioClip(url.toExternalForm());
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
            backgroundMusicMap.put(id, backgroundMusic);
        
    }
    
    public void playSoundEffect(String id){
        Runnable sePlay = new Runnable(){
            @Override
            public void run(){
                soundEffectsMap.get(id).play(volumeSE);
            }
        };
        soundPool.execute(sePlay);
    }
    
    public void playBackgroundMusic(String id){
        //backgroundMusicMap.get(id).setCycleCount(AudioClip.INDEFINITE);
        Runnable bgPlay = new Runnable(){
            @Override
            public void run(){
                backgroundMusicMap.get(id).play(volumeBG);
            }
        };
        soundPool.execute(bgPlay);
    }
    
    public void playBackgroundWithIntro(String introID, String loopID){
        backgroundMusicMap.get(introID).setCycleCount(1);
        System.out.println("Playing intro");
        playBackgroundMusic(introID);
        System.out.println(backgroundMusicMap.get(introID).isPlaying());
        //somehow have it wait for it to finish before playing next part
        
        //System.out.println("Playing loop");
        //playBackgroundMusic(loopID);   
    }
    
    public AudioClip getBackgroundMusic(String id){
        return backgroundMusicMap.get(id);
    }
    /*
    public void muteBG(){
        //loop through all BG and do not play??
    }
    
    public void muteSE(){
        //loop through all SE and do not play??
    }
    */
    
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
