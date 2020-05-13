package fr.pederobien.persistence.impl;

import java.nio.file.Path;

import fr.pederobien.persistence.interfaces.IPersistence;
import fr.pederobien.persistence.interfaces.IUnmodifiableNominable;

public abstract class AbstractPersistence<T extends IUnmodifiableNominable> implements IPersistence<T> {
	private Path path;

	protected AbstractPersistence(Path path) {
		setInternalPath(path);
	}

	@Override
	public Path getPath() {
		return path;
	}

	@Override
	public void setPath(Path path) {
		throw new UnsupportedOperationException("This method cannot be called");
	}

	@Override
	public boolean forceUpdate() {
		return false;
	}

	protected void setInternalPath(Path path) {
		this.path = path;
	}
}
