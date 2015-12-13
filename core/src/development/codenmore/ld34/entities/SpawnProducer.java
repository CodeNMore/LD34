package development.codenmore.ld34.entities;

public interface SpawnProducer {

	public Entity getNew(EntityManager manager, float x, float y);
	
}
