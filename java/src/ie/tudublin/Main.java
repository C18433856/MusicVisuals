package ie.tudublin;

import C18433856.myFFT;

public class Main
{	

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