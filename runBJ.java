import java.io.*;

public class runBJ
{
	public static void main(String[] args) 
	{
		blackJackBetting bj = new blackJackBetting();
		bj.shuffle();
		bj.playBJ();
	}
}