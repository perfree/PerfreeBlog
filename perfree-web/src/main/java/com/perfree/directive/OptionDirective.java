package com.perfree.directive;

import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.perfree.cache.OptionCacheService;
import com.perfree.commons.MultipleSiteUtil;
import com.perfree.commons.SpringBeanUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * option模板指令
 */
@TemplateDirective("option")
@Component
public class OptionDirective extends BaseDirective {

    public void setExprList(ExprList exprList) {
        super.setExprList(exprList);
    }

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        OptionCacheService optionCacheService = SpringBeanUtils.getBean(OptionCacheService.class);
        String defaultValue = null;
        if (this.exprList.length() >= 2){
            defaultValue = getParam(1, scope).toString();
        }
        String result = optionCacheService.getDefaultValue(getParam(0, scope).toString(), MultipleSiteUtil.currentSite(), defaultValue);
        write(writer, StringUtils.isBlank(result) ? "" : result);
    }

    @Override
    public boolean hasEnd() {
        return false;
    }
}
