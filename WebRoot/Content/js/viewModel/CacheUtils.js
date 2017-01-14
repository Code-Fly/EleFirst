/**
 * Created by Administrator on 2016/8/31.
 */
/**
 * session cache
 * @type {{set: SessionCache.set, get: SessionCache.get, remove: SessionCache.remove, update: SessionCache.update, clear: SessionCache.clear}}
 */
var SessionCache = {
    set: function (key, value) {
        sessionStorage.setItem(key, value);
    },
    get: function (key) {
        return sessionStorage.getItem(key);
    },
    remove: function (key) {
        return sessionStorage.removeItem(key);
    },
    update: function (key, value) {
        sessionStorage.removeItem(key);
        return sessionStorage.setItem(key, value);
    },
    clear: function () {
        return sessionStorage.clear();
    }
};

/**
 * local cache
 * @type {{set: LocalCache.set, get: LocalCache.get, remove: LocalCache.remove, update: LocalCache.update, clear: LocalCache.clear}}
 */
var LocalCache = {
    set: function (key, value) {
        localStorage.setItem(key, value);
    },
    get: function (key) {
        return localStorage.getItem(key);
    },
    remove: function (key) {
        return localStorage.removeItem(key);
    },
    update: function (key, value) {
        localStorage.removeItem(key);
        return localStorage.setItem(key, value);
    },
    clear: function () {
        return localStorage.clear();
    }
};