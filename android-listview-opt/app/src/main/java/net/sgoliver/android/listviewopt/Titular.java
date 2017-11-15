package net.sgoliver.android.listviewopt;

import android.widget.ImageView;

public class Titular
{
	private ImageView titulo;
	private String subtitulo;

	public Titular(ImageView tit, String sub){
		titulo = tit;
		subtitulo = sub;
	}
	
	public ImageView getTitulo(){
		return titulo;
	}
	
	public String getSubtitulo(){
		return subtitulo;
	}
}
