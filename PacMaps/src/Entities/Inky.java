package Entities;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import Maps.Line;

public class Inky extends Ghost
{
	private int count = 0;
	
	public Inky(Line line)
	{
		super(line);
		try
		{
			one = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("inky.png"));
			one = Scalr.resize((BufferedImage) one, 12, (BufferedImageOp[])null);
			two = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("inky2.png"));
			two = Scalr.resize((BufferedImage) two, 12, (BufferedImageOp[])null);
		}
		catch (IOException e) { }
		image = one;
	}

	@Override
	public void tick()
	{
		if (++count % 3 == 0)
		{
			count = 0;
			image = image.equals(one) ? two : one;
		}
		move();
	}
}
