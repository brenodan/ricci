
package com.example.riccilib.ricci.Utils;

public class RemoteImplementation implements RemoteInterface {

	private Object object;

	@Override
	public Object getObject() { return object; }

	@Override
	public void setObject(Object object) { this.object = object; }

	@Override
	public boolean equals(Object arg0) {

		try {
			return this.getObject().equals( arg0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return false;
	}

	@Override
	public String toString() {

		try {
			return this.getObject().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}

	@Override
	public int hashCode() {

		try {
			return this.getObject().hashCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return -1;
	}
}