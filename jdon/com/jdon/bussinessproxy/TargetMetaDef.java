/**
 * Copyright 2003-2006 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jdon.bussinessproxy;

import java.io.Serializable;

import com.jdon.bussinessproxy.target.TargetObjectFactory;


/**
 * meta definition for service
 *
 *
 * @author banq
 */
public interface TargetMetaDef extends Serializable {

  boolean isEJB();
  
  String getName();

  String getClassName();

  String getCacheKey();
  
  Class[] getInterfaces();
  
  void setInterfaces(Class[] interfaces);
  
  TargetObjectFactory getTargetObjectFactory();
  
  


}
