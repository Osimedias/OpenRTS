package model.battlefield.army;

import geometry.tools.LogUtil;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.battlefield.Battlefield;
import model.battlefield.army.components.SerializableUnit;
import model.battlefield.army.components.Unit;
import model.battlefield.warfare.Faction;
import model.builders.definitions.BuilderLibrary;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Stores units and factions, and provide a serializable version of the initial situation
 */
@Root
public class Engagement {
	public Battlefield battlefield;
	public BuilderLibrary lib;
	public List<Faction> factions = new ArrayList<>();

	@ElementList
	public List<SerializableUnit> initialEngagement = new ArrayList<>();

	public Engagement() {
		Faction f1 = new Faction(Color.red, "1");
		Faction f2 = new Faction(Color.blue, "2");
		f1.setEnemy(f2);
		factions.add(f1);
		factions.add(f2);
	}

	public Engagement(Battlefield battlefield, BuilderLibrary lib) {
		Faction f1 = new Faction(Color.red, "1");
		Faction f2 = new Faction(Color.blue, "2");
		f1.setEnemy(f2);
		factions.add(f1);
		factions.add(f2);

		this.lib = lib;
		this.battlefield = battlefield;
	}

	public void addUnit(Unit u) {
		battlefield.armyManager.registerUnit(u);
	}

	public void removeUnit(Unit u) {
		battlefield.armyManager.unregisterUnit(u);
	}

	public void resetEngagement() {
		LogUtil.logger.info("reseting engagement");
		battlefield.armyManager.reset();

		for (SerializableUnit up : initialEngagement) {
			battlefield.armyManager.registerUnit(up.getUnit(lib, factions));
		}
	}

	public void saveEngagement() {
		initialEngagement.clear();
		for (Unit u : battlefield.armyManager.getUnits()) {
			initialEngagement.add(new SerializableUnit(u));
		}
	}
}
