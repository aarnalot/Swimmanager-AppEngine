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

@Api(name = "eventoendpoint", namespace = @ApiNamespace(ownerDomain = "brainstorm.com", ownerName = "brainstorm.com", packagePath = "swimmanager"))
public class EventoEndpoint
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
	@ApiMethod(name = "listEvento")
	public CollectionResponse<Evento> listEvento(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit)
	{

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Evento> execute = null;

		try
		{
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Evento as Evento");
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

			execute = (List<Evento>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Evento obj : execute)
				;
		} finally
		{
			mgr.close();
		}

		return CollectionResponse.<Evento> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getEvento")
	public Evento getEvento(@Named("id") Long id)
	{
		EntityManager mgr = getEntityManager();
		Evento evento = null;
		try
		{
			evento = mgr.find(Evento.class, id);
		} finally
		{
			mgr.close();
		}
		return evento;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param evento the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertEvento")
	public Evento insertEvento(Evento evento)
	{
		EntityManager mgr = getEntityManager();
		try
		{
			if (containsEvento(evento))
			{
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(evento);
		} finally
		{
			mgr.close();
		}
		return evento;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param evento the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateEvento")
	public Evento updateEvento(Evento evento)
	{
		EntityManager mgr = getEntityManager();
		try
		{
			if (!containsEvento(evento))
			{
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(evento);
		} finally
		{
			mgr.close();
		}
		return evento;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 * @return The deleted entity.
	 */
	@ApiMethod(name = "removeEvento")
	public Evento removeEvento(@Named("id") Long id)
	{
		EntityManager mgr = getEntityManager();
		Evento evento = null;
		try
		{
			evento = mgr.find(Evento.class, id);
			mgr.remove(evento);
		} finally
		{
			mgr.close();
		}
		return evento;
	}

	private boolean containsEvento(Evento evento)
	{
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try
		{
			Evento item = mgr.find(Evento.class, evento.getKey());
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
