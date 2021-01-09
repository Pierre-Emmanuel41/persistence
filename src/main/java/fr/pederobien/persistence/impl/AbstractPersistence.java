package fr.pederobien.persistence.impl;

import java.nio.file.Path;

import fr.pederobien.persistence.interfaces.IPersistence;

public abstract class AbstractPersistence<T> implements IPersistence<T> {
	private Path path;

	protected AbstractPersistence(Path path) {
		this.path = path;
	}

	@Override
	public Path getPath() {
		return path;
	}

	@Override
	public void setPath(Path path) {
		this.path = path;
	}

	@Override
	public boolean forceUpdate() {
		return false;
	}

	@Override
	public void update() {

	}
}
