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
	private float waveTimer = 0f, betweenWaveTime = 10.0f;
	private boolean haltSpawn = true;

	public WaveManager(EntityManager manager) {
		this.manager = manager;
	}

	public void tick(float delta) {
		if (haltSpawn) {
			for (Spawner s : manager.getSpawners()) {
				s.setTimer(0.0f);
			}
			if (manager.getEntities().size <= 0) {
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

		waveEnemyLength = MathUtils.clamp(waveNum * 5, 5, 100);
		Slime.HEALTH_MULTIPLIER = MathUtils.clamp(waveNum * 0.75f, 1.0f, 4.5f);

		Vec2 pos = getRandomSpawnerPos();
		if (waveNum <= 3) {
			manager.addSpawner(new Spawner(manager, pos.x, pos.y, MathUtils
					.clamp(MathUtils.random(3, 5) * waveNum, 5.0f, 15.0f),
					MathUtils.clamp(MathUtils.random(20, 25) * waveNum / 2,
							15.0f, 30.0f), MathUtils.random(waveNum,
							waveNum * 3), waveNum * 4, new SpawnProducer() {
						@Override
						public Entity getNew(EntityManager manager, float x,
								float y) {
							return new Slime(manager, x, y);
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
