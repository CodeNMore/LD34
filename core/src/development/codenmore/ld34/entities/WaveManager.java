package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.utils.Vec2;

public class WaveManager {

	private EntityManager manager;
	private int waveNum = 0, spawned = 0, waveEnemyLength = 3;
	private float waveTimer = 0f, betweenWaveTime = 25.0f;
	private boolean haltSpawn = true, rewardGiven = true;

	public WaveManager(EntityManager manager) {
		this.manager = manager;
	}

	public void tick(float delta) {
		if (haltSpawn) {
			for (Spawner s : manager.getSpawners()) {
				s.setTimer(0.0f);
			}
			if (manager.getEntities().size <= 0) {
				if(!rewardGiven){
					manager.getWorld().getGameState().getHud().incResources(waveNum * 50);
					manager.getWorld().getGameState().getHud().incEnergy(waveNum * 40);
					if(MathUtils.randomBoolean(0.1f))
						manager.getWorld().getGameState().getHud().incFood(MathUtils.random(3, 6));
					rewardGiven = true;
					Assets.playSound("award");
				}
				waveTimer += delta;
				if (waveTimer > betweenWaveTime) {
					newWave();
				}
			}
		}
	}

	private void newWave() {
		haltSpawn = false;
		spawned = 0;
		waveTimer = 0.0f;
		waveNum++;
		manager.getWorld().reHealTiles();// TODO: Every other wave??
		rewardGiven = false;

		if (waveNum == 1) {
			waveEnemyLength = 5;
			Vec2 pos = getRandomSpawnerPos();
			manager.addSpawner(new Spawner(manager, pos.x, pos.y, 8, 14, 1, 2,
				new SpawnProducer() {
					@Override
					public Entity getNew(EntityManager manager, float x, float y) {
						return new Slime(manager, x, y, 1.0f, 1.0f);
					}
			}));
		}else if(waveNum == 2){
			waveEnemyLength = 8;
			Vec2 pos = getRandomSpawnerPos();
			manager.addSpawner(new Spawner(manager, pos.x, pos.y, 12, 20, 2, 3,
				new SpawnProducer() {
					@Override
					public Entity getNew(EntityManager manager, float x, float y) {
						return new Slime(manager, x, y, 1.0f, 1.0f);
					}
			}));
		}else if(waveNum == 3){
			waveEnemyLength = 13;
			Vec2 pos = getRandomSpawnerPos();
			manager.addSpawner(new Spawner(manager, pos.x, pos.y, 22, 24, 2, 3,
				new SpawnProducer() {
					@Override
					public Entity getNew(EntityManager manager, float x, float y) {
						return new SlimeR(manager, x, y, 1.0f, 1.0f);
					}
			}));
		}else if(waveNum == 4){
			waveEnemyLength = 16;
			Vec2 pos = getRandomSpawnerPos();
			manager.addSpawner(new Spawner(manager, pos.x, pos.y, 22, 30, 1, 1,
				new SpawnProducer() {
					@Override
					public Entity getNew(EntityManager manager, float x, float y) {
						return new Tank(manager, x, y, 1.0f, 1.0f);
					}
			}));
		}else if(waveNum == 5){
			waveEnemyLength = 23;
		}else if(waveNum == 6){
			waveEnemyLength = 28;
			Vec2 pos = getRandomSpawnerPos();
			manager.addSpawner(new Spawner(manager, pos.x, pos.y, 22, 24, 2, 3,
				new SpawnProducer() {
					@Override
					public Entity getNew(EntityManager manager, float x, float y) {
						return new SlimeR(manager, x, y, 1.2f, 1.2f);
					}
			}));
		}
	}

	public void render(SpriteBatch batch) {
		if (haltSpawn && manager.getEntities().size <= 0) {
			Assets.getFont().getData().setScale(0.8f);
			Assets.getFont().setColor(Color.NAVY);
			Assets.getFont().draw(
					batch,
					"Wave " + (waveNum + 1) + " starts in "
							+ (int) (betweenWaveTime - waveTimer) + "s",
					Main.WIDTH / 2 - 150, Main.HEIGHT - 8);
		}
	}

	private Vec2 getRandomSpawnerPos() {
		Vec2 r = new Vec2();
		if (MathUtils.randomBoolean()) {
			r.x = MathUtils.random(1, 10);
		} else {
			r.x = MathUtils.random(manager.getWorld().getWidth() - 10, manager
					.getWorld().getWidth() - 1);
		}

		if (MathUtils.randomBoolean()) {
			r.y = MathUtils.random(4, 12);
		} else {
			r.y = MathUtils.random(manager.getWorld().getHeight() - 10, manager
					.getWorld().getHeight() - 1);
		}
		System.out.println("Random spawn pos: " + r.x + "  " + r.y);
		return r;
	}

	public void addedEntity() {
		spawned++;
		if (spawned >= waveEnemyLength) {
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
