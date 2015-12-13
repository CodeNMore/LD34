package development.codenmore.ld34.entities;

import java.util.Comparator;

import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.utils.Vec2;


public class AStar {
	
	private static Array<Node> open = new Array<Node>(), closed = new Array<Node>();
	private static final Comparator<Node> listComp = new Comparator<Node>(){
		@Override
		public int compare(Node a, Node b) {
			if(a.calculateF() < b.calculateF())
				return -1;
			else if(a.calculateF() > b.calculateF())
				return 1;
			return 0;
		}
	};
	
	private AStar(){}
	
	public static void generatPath(int ix, int iy, int dx, int dy, Array<Vec2> path){
		path.clear();
		open.clear();
		closed.clear();
		Node.dest = new Vec2(dx, dy);
		
		Node s = null;
		open.add( new Node(ix, iy, null));
		
		for(int q = 0;q < 10000;++q){
			open.sort(listComp);
			s = open.get(0);
			open.removeIndex(0);
			closed.add(s);
			if(s.pos.x == dx && s.pos.y == dy){
				break;
			}
			
			addCheckNode(s.pos.x + 1, s.pos.y, s, open, closed);
			addCheckNode(s.pos.x - 1, s.pos.y, s, open, closed);
			addCheckNode(s.pos.x, s.pos.y + 1, s, open, closed);
			addCheckNode(s.pos.x, s.pos.y - 1, s, open, closed);
			
			System.out.println("Q:" + q);
			if(q == 9999){
				System.err.println("THE A STAR LOOP EXITED FORCEFULLY AT 20000");
			}
		}
		
		// Calculate path from s
		open.clear();
		open.add(s);
		Node p = s.parent;
		while(p != null){
			open.add(p);
			p = p.parent;
		}
		
		for(int i = open.size - 1;i >= 0;--i){
			path.add(open.get(i).pos);
		}
	}
	
	private static void addCheckNode(int x, int y, Node parent, Array<Node> open, Array<Node> closed){
		if(x < 0 || y < 0 || x >= Node.world.getWidth() || y >= Node.world.getHeight())
			return;
		if(containsNodePoint(x, y, closed) != null)
			return;
		Node n = containsNodePoint(x, y, open);
		Node newNode = new Node(x, y, parent);
		if(n != null){
			if(n.calculateG() < newNode.calculateG())
				n.parent = parent;
			return;
		}
		open.add(newNode);
	}
	
	private static Node containsNodePoint(int x, int y, Array<Node> list){
		for(Node n : list){
			if(n.pos.x == x && n.pos.y == y)
				return n;
		}
		return null;
	}

}
