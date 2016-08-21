/*
 * The MIT License
 *
 * Copyright 2016 Dmitry Noranovich <javaeeeee at gmail dot com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.greenlaw110.bookmark.endpoint;

import act.cli.Required;
import act.controller.Controller;
import act.db.DbBind;
import com.greenlaw110.bookmark.model.Bookmark;
import com.greenlaw110.bookmark.model.User;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;
import java.util.Collection;

/**
 * The controller that exposes resource methods to work with bookmarks for a
 * particular user.
 *
 * @author Dmitry Noranovich <javaeeeee at gmail dot com>
 */
@Controller("/{username}/bookmarks")
public class BookmarksService extends Controller.Util {

    @Inject
    @DbBind(value = "username", byId = false)
    private User user;

    @Inject
    private User.Dao userDao;

    @Required("specify the username")
    private String username;

    /**
     * If the user does not exist, then respond with 404
     */
    @Before
    public void validateUser() {
        notFoundIfNull(user);
    }

    /**
     * A method to return bookmarks for a particular user.
     *
     * @return list of user's bookmarks.
     */
    @GetAction
    public Collection<Bookmark> getAllBookmarks() {
        return user.getBookmarks();
    }

    /**
     * A method to find a bookmark by id.
     *
     * @param bookmarkId
     */
    @GetAction("{bookmarkId}")
    public Bookmark getBookmark(@Required("specify the bookmark ID") Long bookmarkId) {
        return user.getBookmark(bookmarkId);
    }


}
