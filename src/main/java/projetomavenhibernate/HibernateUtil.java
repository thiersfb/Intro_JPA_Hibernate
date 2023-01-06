package projetomavenhibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;
	
	static {
		init();
	}
	
	private static void init() {
		
		try {
			
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("projeto-maven-hibernate"); // atributo name da persistence-unit no arquivo persistence.xml, que deve sempre estar dentro da pasta META-INF
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager(); /* Prove a parte de persistencia */
	}
	
	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity); /* Retorna a PK */
	}
	
}
