package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("3d")
public class Punto3D extends Punto {
	private int z = 3;

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Punto3D [z=" + z + ", x=" + getX() + ", y=" + getY() + "]";
	}
	
	
}
