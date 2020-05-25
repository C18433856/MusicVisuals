package C18433856;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class myFFT extends Visual
{

    float radius = 300;     // Used to compute distance between the spheres in visual mode 2
    int option = 1;     // Used to choose the visual mode

    public void settings()
    {
        size(512, 512, P3D);        // A screen size that matches the frequency bands number

    }

    public void setup()
    {
        background(0);      // Set a black background
        startMinim();
        stroke(255);    // Set white outline
        //startListening();     // To swap to microphone input
        loadAudio("heroplanet.mp3");       // Load the song to be played
        getAudioPlayer().play();        // Play the loaded song
        colorMode(HSB);     // Swap the color more to HSB from RGB
    }

    float angle = 0;    // Used for rotation
    int i = 0;      // index variable
    
    
    public void draw(){


        if (option == 1){      //visual mode 1
            background(0);
                getFFT().forward(getAudioBuffer()); // Apply FFT on the sound in the buffer

            for(int j = 0; j <= getFrameSize(); j++){   //For each frame

                float y = map(getFFT().getFreq(j), 0, 83, height, 0);   // Get y coordinate for line
                line(j, height, j, y);      // draw line
            }
        }
        else if(option == 2){       //visual mode 2
            
            calculateAverageAmplitude();
            try{
                calculateFFT();
            }
            catch(VisualException e){
                e.printStackTrace();
            }
            
            calculateFrequencyBands();

            background(0);  // Set background to black
            noFill();       // Empty shapes
            stroke(255);    // White outline
            lights();       // Set lighting

            float[] bands = getSmoothedBands();

            for(int i = 0;i < getBands().length; i++){  //For each frequency band

                float theta = map(i, 0, bands.length, 0, TWO_PI); // get an angle

                // Compute the cordinates for the spheres
                float x = sin(theta) * radius;
                float z = cos(theta) * radius;

                float size = bands[i]/5;    // Size of balls based on amplitude of certain frequencies

                pushMatrix();   // Apply the settings only to the shapes inside of this
                camera(350, -700, 0, 0, 0, 0, 0, 1, 0);     // Set the camera position
                translate(x, 0 , z);    // Place the spheres
                fill(map(i, 0, getBands().length, 0, 255), 255, 255);   //Fill the spheres with distinct colours based on the frequency they represent
                sphere(size);   // Draw the sphere
                popMatrix();    // Remove the settings
            }
        }
        else if(option == 3){   //visual mode 3
            
            // An alternative background changing mechanism
            // if (i == 0){
            //    background(random(0, 255), 255, 255);
            //    i = 30; 
            // }
            // else{
            //    i--;
            // }

            stroke(255);    // Set the outline colour to white
            calculateAverageAmplitude(); 
            if(getAmplitude() > 0.3f)   // If the amplitude is greater than a certain value
            {
                background(random(0, 255), 255, 255);   //Change background colour to a random colour
            }
            lights();
            fill(map(getAmplitude(), 0, 1, 0, 255), 255, 255);
            pushMatrix();
            translate(width/2, height/2, 0);    // Set the shape in the middle of the screen
            rotateX(angle); //Rotate along the X axis
            rotateY(angle); // Rotate along the Y axis
            //rotateZ(angle);
            float sphereSize = (100 + (getSmoothedAmplitude() * 100));
            sphere(sphereSize);
            popMatrix();

            angle += 0.02f;
            if (angle > TWO_PI){   //If angle exceeds 360 degrees
               angle = 0f;
            }
        }
            
    }

    public void keyPressed()
    {
        if(key == 'p' && getAudioPlayer().isPlaying()){     // If the song is playing and key 'p' is pressed
            getAudioPlayer().pause();
        }
        else if(key == 'p'){    // If the song is not playing and the key 'p' is pressed
            getAudioPlayer().play();   
        }

        if(key == 'r'){     // Restart Song
            getAudioPlayer().cue(0);       // Set the song time at it's start
        }

        if(key=='n'){   // Swap to the next visual mode
            if(option == 1)     // If currently in visual mode 1
            {
                option = 2;
            }
            else if(option == 2){       // If currently in visual mode 2
                option = 3;
                background(0);
            }
            else if(option == 3){       // If currently in visual mode 3
                option = 1;     // swap to visual mode 1
            }
        }


    }
}