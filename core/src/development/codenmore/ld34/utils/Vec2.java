package development.codenmore.ld34.utils;

public class Vec2 {

	public int x, y;

	public Vec2(int x, int y) {
		set(x, y);
	}
	
	public void inc(int x, int y){
		this.x += x;
		this.y += y;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
