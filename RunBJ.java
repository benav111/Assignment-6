import java.io.*;

public final class RunBJ
{
	public static void main(String[] args) 
	{
		//Play BlackJack
		BlackJackBetting bj = new BlackJackBetting();
		bj.shuffle();
		bj.playBJ();
	}
}