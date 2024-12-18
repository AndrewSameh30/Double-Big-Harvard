package controller;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import GUI.Start;
import GUI.Writefile;
import GUI.fileview;
import GUI.outputview;
import GUI.secondview;

public class controller {
 private controller controller;
 private secondview secondview;
 private Start start;
  public controller() {
	  Start Start = new Start(controller);
	  Start.setController(this);
  }
	
  public controller getController() {
	return controller;
}
public void setController(controller controller) {
	this.controller = controller;
}
public secondview getSecondview() {
	return secondview;
}
public void setSecondview(secondview secondview) {
	this.secondview = secondview;
}
public Start getStart() {
	return start;
}
public void setStart(Start start) {
	this.start = start;
}
public void tosecondview() {
	  secondview secondview = new secondview(this);
	  secondview.setController(this);
	  secondview.setVisible(true);
  }
public void tofileview() {
	 fileview fileview = new fileview(this);
	 fileview.setController(this);
	 fileview.setVisible(true);
}
public void tooutputview(String x) {
	outputview outputview = new outputview(this);
	outputview.setLabelText(x);
	outputview.setController(this);
	outputview.setVisible(true);
}
public void towritefile() {
	 Writefile Writefile = new Writefile(this);
	 Writefile.setController(this);
	 Writefile.setVisible(true);
}

public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException
{
	 controller controller = new controller() ;	
	
}




  }