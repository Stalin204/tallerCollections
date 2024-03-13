package interfaces;

import java.util.ArrayList;

public interface ICRUD<E> {
	Boolean create(E E);
	Boolean delete(String ID);
	Boolean update(E E2);
	E get(String ID);
	Boolean exist(String ID);
	Boolean updateID(String IDOld, String IDNew);

}
