package Model;

import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable{
	private int type;
	private InetAddress sender;
	private String reciever;
	private String player;
	private double score;
	public Message(int type,InetAddress sender,String reciever,String player,double score) {
		this.type = type;
		this.sender = sender;
		this.reciever = reciever;
		this.player = player;
		this.score = score;
	}
	public int getType() {
		return type;
	}
	public InetAddress getSender() {
		return sender;
	}
	public String getReciever() {
		return reciever;
	}
	public String getPlayer() {
		return player;
	}
	public double getScore() {
		return score;
	}
}
