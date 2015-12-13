package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;


public class WaveManager {
	
	private EntityManager manager;
	private int waveNum = 1, spawned = 0, waveEnemyLength = 6;
	private float waveTimer = 0f, betweenWaveTime = 25.0f;
	private boolean haltSpawn = true;
	
	public WaveManager(EntityManager manager){
		this.manager = manager;
		
		manager.addSpawner(new Spawner(manager, 10, 10, 4, 9, 1, 3, new SpawnProducer() {
			@Override
			public Entity getNew(EntityManager manager, float x, float y) {
				return new Slime(manager, x, y);
			}
		}));
	}
	
	public void tick(float delta){
		if(haltSpawn){
			waveTimer += delta;
			if(waveTimer > betweenWaveTime){
				haltSpawn = false;
				spawned = 0;
				waveTimer = 0.0f;
				waveNum++;
				newWave();
			}
			for(Spawner s : manager.getSpawners()){
				s.setTimer(0.0f);
			}
		}
	}
	
	private void newWave(){
		waveEnemyLength = 5;//TODO: this
	}
	
	public void render(SpriteBatch batch){
		if(haltSpawn && manager.getEntities().size <= 0){
			Assets.getFont().getData().setScale(0.8f);
			Assets.getFont().setColor(Color.NAVY);
			Assets.getFont().draw(batch, "Next Wave: " + (int) (betweenWaveTime - waveTimer) + "s", Main.WIDTH / 2 - 96, Main.HEIGHT - 8);
		}
	}
	
	public void addedEntity(){
		spawned++;
		if(spawned >= waveEnemyLength){
			haltSpawn = true;
		}
	}

	public int getWaveNum() {
		return waveNum;
	}

	public void setWaveNum(int waveNum) {
		this.waveNum = waveNum;
	}

}
