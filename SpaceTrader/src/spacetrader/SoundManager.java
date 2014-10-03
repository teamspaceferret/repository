/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
    
    public void loadSoundEffect(String id, URL url){
        AudioClip soundEffect = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, soundEffect);
    }
    
    public void loadBackgroundSound(String id, URL url){
        AudioClip backgroundMusic = new AudioClip(url.toExternalForm());
        backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
        backgroundMusicMap.put(id, backgroundMusic);
    }
    
    public void playSoundEffect(String id){
        soundEffectsMap.get(id).play(volumeSE);
    }
    
    public void playBackgroundMusic(String id){
        backgroundMusicMap.get(id).setCycleCount(AudioClip.INDEFINITE);
        backgroundMusicMap.get(id).play(volumeBG);
    }
    
    public void playBackgroundWithIntro(String introID, String loopID){
        backgroundMusicMap.get(introID).play(volumeBG);
        while(backgroundMusicMap.get(introID).isPlaying()){
            //wait weeeee
        }
        playBackgroundMusic(loopID);   
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
    
    
    
    
    
    
}
