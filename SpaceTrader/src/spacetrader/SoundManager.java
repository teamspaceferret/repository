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
    
    public SoundManager(){
        soundPool = Executors.newFixedThreadPool(6);
    }
    
    public SoundManager(int threadNumber){
        soundPool = Executors.newFixedThreadPool(threadNumber);
    }
    
    public void loadSoundEffect(String id, URL url){
        AudioClip soundEffect = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, soundEffect);
        
    }
    
    public void loadBackgroundSound(String id, URL url){
        AudioClip backgroundMusic = new AudioClip(url.toExternalForm());
        backgroundMusicMap.put(id, backgroundMusic);
    }
    
    
    
    
}
