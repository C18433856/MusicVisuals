# Music Visualiser Project

Name: Valentin Nache

Student Number: C18433856

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment

My assignment revolves around exploring the different elements of a song. Using the program, the user may visualise the amplitude of the song,
or the amplitude of the song at different frequency levels.

# Instructions

The program is very easy to use as it only has a few hotkeys. The user may press 'p' to pause or start, 'r' to restart the song, and'n' for the next visualisation 
mode(3 are included).

# How it works

The program is split in different display modes. The first mode displays the amplitude of the song playing at different frequency levels in a bar chart
fashion. This uses FFT to retrieve the amplitude of the song at different frequency levels. The second display mode does the same thing, however,
it is implemented using different FFT functionality, and the frequency at a given amplitude band is represented by the size of an uniquely coloured sphere.
The third display mode deals only with the amplitude of the song as a whole. The background will change when the frequency exceeds a certain level, and the
size and colour of the sphere in the middle of the screen depends on the amplitude of the song.

# What I am most proud of in the assignment

For the first display mode, I tried to implement the frequency amplitude analysis using the minim library documentation. I managed to get it working and this
is the aspect I am most proud of.


