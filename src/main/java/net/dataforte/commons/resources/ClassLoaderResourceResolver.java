/**
 * Copyright 2010 Tristan Tarrant
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dataforte.commons.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observer;


/**
 * A ResourceResolver which supports resolving classes either via the ClassLoader or via
 * the filesystem
 * 
 * @author Tristan Tarrant
 */
public class ClassLoaderResourceResolver extends AResourceResolver {

	@Override
	public InputStream getResource(String name, Observer observer, long delay, ThreadGroup threadGroup) {
		
		// Attempt to load from current class loader first
		InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		if (is == null) {
			try {
				// Attempt to load from simple file name
				is = new FileInputStream(name);
			} catch (FileNotFoundException e) {
				// Ignore
			}
		}
		return is;
	}

}
