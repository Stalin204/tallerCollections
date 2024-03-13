package library;
import exception.OutLimitException;
import exception.NoElementsException;

public class Library {

		/*-------------------------------------------------metodos para verificar--------------------------------------------------------------------------------------------------------*/

		/**
		 * verificarLimite
		 * @param i
		 * @param tam
		 * @throws OutLimitException
		 * @throws NoElementsException
		 */
		public static void checkLimit(int i, int tam)throws OutLimitException, NoElementsException{
			if(tam== 0){
				throw new NoElementsException();
			}
			if(i >= tam){
				throw new OutLimitException(i, tam);
			}
		}

}