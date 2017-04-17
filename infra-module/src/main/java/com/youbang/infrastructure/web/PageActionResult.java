
package com.youbang.infrastructure.web;


public class PageActionResult<T> extends ActionResult<T>
{

    /**
     * 分页对象
     */
    private Page page;

    public PageActionResult()
    {

    }

    public PageActionResult(int code, String message, T value, Page page)
    {
        this.code = code;
        this.message = message;
        this.value = value;
        this.page = page;
    }

    public Page getPage()
    {
        return page;
    }

    public void setPage(Page page)
    {
        this.page = page;
    }

}
