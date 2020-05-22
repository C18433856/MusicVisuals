package ie.tudublin;

import C18433856.CubeVisual;
import C18433856.MyVisual;
import C18433856.RotatingAudioBands;
import C18433856.myFFT;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new RotatingAudioBands());		
	}

	public void testUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new myFFT());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.testUI();			
	}
}