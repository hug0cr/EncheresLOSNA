/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.util.List;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 10:27:23
 */
public interface DAO<T> {
	public void insert(T t);
	public void update(T t);
	public T selectById(int id);
	public List<T> selectAll();
	public void delete(T t);
}
