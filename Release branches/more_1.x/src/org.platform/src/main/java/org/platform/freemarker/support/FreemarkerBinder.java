/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.platform.freemarker.support;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.platform.freemarker.IFmTag;
import org.platform.freemarker.ITemplateLoaderCreator;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.internal.UniqueAnnotations;
/**
 * 
 * @version : 2013-5-22
 * @author ������ (zyc@byshell.org)
 */
public class FreemarkerBinder implements Module {
    private List<TemplateLoaderCreatorDefinition> templateLoaderDefinition = new ArrayList<TemplateLoaderCreatorDefinition>();
    private List<FmMethodDefinition>              fmMethodDefinition       = new ArrayList<FmMethodDefinition>();
    private List<FmTagDefinition>                 fmTagDefinition          = new ArrayList<FmTagDefinition>();
    /***/
    public void bindTemplateLoaderCreator(String name, Class<ITemplateLoaderCreator> templateLoaderCreatorType) {
        this.templateLoaderDefinition.add(new TemplateLoaderCreatorDefinition(name, templateLoaderCreatorType));
    }
    /***/
    public void bindTag(String tagName, Class<IFmTag> fmTagType) {
        this.fmTagDefinition.add(new FmTagDefinition(tagName, fmTagType));
    }
    /***/
    public void bindMethod(String funName, Method fmMethodType) {
        this.fmMethodDefinition.add(new FmMethodDefinition(funName, fmMethodType));
    }
    @Override
    public void configure(Binder binder) {
        for (TemplateLoaderCreatorDefinition define : this.templateLoaderDefinition) {
            binder.bind(TemplateLoaderCreatorDefinition.class).annotatedWith(UniqueAnnotations.create()).toInstance(define);
        }
        for (FmTagDefinition define : this.fmTagDefinition) {
            binder.bind(FmTagDefinition.class).annotatedWith(UniqueAnnotations.create()).toInstance(define);
        }
        for (FmMethodDefinition define : this.fmMethodDefinition) {
            binder.bind(FmMethodDefinition.class).annotatedWith(UniqueAnnotations.create()).toInstance(define);
        }
    }
}