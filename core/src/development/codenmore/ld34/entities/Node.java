package development.codenmore.ld34.entities;

import development.codenmore.ld34.utils.Vec2;
import development.codenmore.ld34.worlds.World;

public class Node {
	
	public static World world;
	public static Vec2 dest;
	public Vec2 pos;
	public Node parent;
	
	public Node(int x, int y, Node parent){
		pos = new Vec2(x, y);
		this.parent = parent;
	}
	
	public int calculateF(){
		return calculateH() + calculateG();
	}
	
	public int calculateG(){
		int p = 0;
		if(parent != null)
			p = parent.calculateG();
		return world.getTile(pos.x, pos.y).getMovementCost() + p;
	}
	
	public int calculateH(){
		int ret = Math.abs(dest.x - pos.x);
		ret += Math.abs(dest.y - pos.y);
		return ret * 2;
	}

}
