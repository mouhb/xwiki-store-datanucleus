/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xpn.xwiki.store.datanucleus;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.xpn.xwiki.doc.XWikiDocument;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.store.EntityProvider;
import org.xwiki.store.datanucleus.internal.DataNucleusPersistableObjectStore;
import org.xwiki.store.objects.PersistableClassLoader;
import com.xpn.xwiki.store.datanucleus.PersistableXWikiDocument;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOObjectNotFoundException;


public class DataNucleusXWikiDocumentProvider implements EntityProvider<XWikiDocument, DocumentReference>
{
    private final PersistenceManager manager;

    public DataNucleusXWikiDocumentProvider(final PersistenceManager manager)
    {
        this.manager = manager;
    }

    public XWikiDocument get(final DocumentReference reference)
    {
        final String[] key = PersistableXWikiDocument.keyGen(reference, "");

        try {
            return this.manager.getObjectById(PersistableXWikiDocument.class, key).toXWikiDocument();
        } catch (JDOObjectNotFoundException e) {
            return null;
        }
    }

    public List<XWikiDocument> get(final List<DocumentReference> references)
    {
        throw new RuntimeException("Not Implemented...............");
    }
}