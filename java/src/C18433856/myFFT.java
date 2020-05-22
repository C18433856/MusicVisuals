package C18433856;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class myFFT extends Visual
{

    float radius = 300;
    int option = 1;

    public void settings()
    {
        size(512, 512, P3D);

    }

    public void setup()
    {
        background(0);
        startMinim();
        stroke(255);
        //startListening();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
        colorMode(HSB);
    }

    float angle = 0;
    int i = 0;
    
    
    public void draw(){


        if (option == 1){
            background(0);
                getFFT().forward(getAudioBuffer());

            for(int j = 0; j <= getFrameSize(); j++){

                float y = map(getFFT().getFreq(j), 0, 83, height, 0);
                line(j, height, j, y);
            }
        }
        else if(option == 2){
            
            calculateAverageAmplitude();
            try{
                calculateFFT();
            }
            catch(VisualException e){
                e.printStackTrace();
            }
            
            calculateFrequencyBands();

            background(0);
            noFill();
            stroke(255);
            lights();

            float[] bands = getSmoothedBands();

            for(int i = 0;i < getBands().length; i++){

                float theta = map(i, 0, bands.length, 0, TWO_PI);

                float x = sin(theta) * radius;
                float z = cos(theta) * radius;

                float size = bands[i]/5;

                pushMatrix();
                camera(350, -700, 0, 0, 0, 0, 0, 1, 0);
                translate(x, 0 , z);
                fill(map(i, 0, getBands().length, 0, 255), 255, 255);
                sphere(size);
                popMatrix();
            }
        }
        else if(option == 3){
            
            // An alternative background changing mechanism
            // if (i == 0){
            //    background(random(0, 255), 255, 255);
            //    i = 30; 
            // }
            // else{
            //    i--;
            // }

            stroke(255);
            calculateAverageAmplitude();
            if(getAmplitude() > 0.3f)
            {
                background(random(0, 255), 255, 255);
            }
            lights();
            fill(map(getAmplitude(), 0, 1, 0, 255), 255, 255);
            pushMatrix();
            translate(width/2, height/2, 0);
            rotateX(angle);
            rotateY(angle);
            //rotateZ(angle);
            float sphereSize = (100 + (getSmoothedAmplitude() * 100));
            sphere(sphereSize);
            popMatrix();

            angle += 0.02f;
            if (angle > TWO_PI){   
               angle = 0f;
            }
        }
            
    }

    public void keyPressed()
    {
        if(key == 'p' && getAudioPlayer().isPlaying()){
            getAudioPlayer().pause();
        }
        else if(key == 'p'){
            getAudioPlayer().play();
        }

        if(key == 'r'){     // Restart Song
            getAudioPlayer().cue(0);
        }

        if(key=='n'){
            if(option == 1)
            {
                option = 2;
            }
            else if(option == 2){
                option = 3;
                background(0);
            }
            else if(option == 3){
                option = 1;
            }
        }


    }
}