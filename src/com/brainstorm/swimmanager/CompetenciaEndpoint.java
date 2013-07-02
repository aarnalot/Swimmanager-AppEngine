package com.brainstorm.swimmanager;

import com.brainstorm.swimmanager.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "competenciaendpoint", namespace = @ApiNamespace(ownerDomain = "brainstorm.com", ownerName = "brainstorm.com", packagePath = "swimmanager"))
public class CompetenciaEndpoint
{

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings(
	{ "unchecked", "unused" })
	@ApiMethod(name = "listCompetencia")
	public CollectionResponse<Competencia> listCompetencia(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit)
	{

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Competencia> execute = null;

		try
		{
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Competencia as Competencia");
			if (cursorString != null && cursorString != "")
			{
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null)
			{
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Competencia>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Competencia obj : execute)
				;
		} finally
		{
			mgr.close();
		}

		return CollectionResponse.<Competencia> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getCompetencia")
	public Competencia getCompetencia(@Named("id") String id)
	{
		EntityManager mgr = getEntityManager();
		Competencia competencia = null;
		try
		{
			competencia = mgr.find(Competencia.class, id);
		} finally
		{
			mgr.close();
		}
		return competencia;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param competencia the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertCompetencia")
	public Competencia insertCompetencia(Competencia competencia)
	{
		EntityManager mgr = getEntityManager();
		try
		{
			if (containsCompetencia(competencia))
			{
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(competencia);
		} finally
		{
			mgr.close();
		}
		return competencia;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param competencia the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateCompetencia")
	public Competencia updateCompetencia(Competencia competencia)
	{
		EntityManager mgr = getEntityManager();
		try
		{
			if (!containsCompetencia(competencia))
			{
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(competencia);
		} finally
		{
			mgr.close();
		}
		return competencia;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 * @return The deleted entity.
	 */
	@ApiMethod(name = "removeCompetencia")
	public Competencia removeCompetencia(@Named("id") String id)
	{
		EntityManager mgr = getEntityManager();
		Competencia competencia = null;
		try
		{
			competencia = mgr.find(Competencia.class, id);
			mgr.remove(competencia);
		} finally
		{
			mgr.close();
		}
		return competencia;
	}

	private boolean containsCompetencia(Competencia competencia)
	{
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try
		{
			Competencia item = mgr.find(Competencia.class, competencia.getNombre());
			if (item == null)
			{
				contains = false;
			}
		} finally
		{
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager()
	{
		return EMF.get().createEntityManager();
	}

}
