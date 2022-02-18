package com.didichuxing.doraemonkit.kit.mc.util

import com.didichuxing.doraemonkit.kit.mc.all.WSEvent
import com.didichuxing.doraemonkit.kit.mc.connect.PackageType
import com.didichuxing.doraemonkit.kit.mc.connect.WSPackage
import com.didichuxing.doraemonkit.util.GsonUtils


object WSPackageUtils {

    fun toPackageJson(event: WSEvent): String {
        return toPackageJson("0", PackageType.BROADCAST, event)
    }

    fun toPackageJson(id: String, type: PackageType, event: WSEvent): String {
        val wsPackage = WSPackage(id, type, GsonUtils.toJson(event))
        return GsonUtils.toJson(wsPackage)
    }

    fun toPackageJson(id: String, type: PackageType, data: String): String {
        val wsPackage = WSPackage(id, type, data)
        return GsonUtils.toJson(wsPackage)
    }

    fun jsonToPackage(data: String): WSPackage {
        val wsPackage = GsonUtils.fromJson<WSPackage>(data, WSPackage::class.java)
        return wsPackage
    }

    fun jsonToEvent(data: String): WSEvent {
        val wsPackage = jsonToPackage(data)
        val wsEvent = GsonUtils.fromJson<WSEvent>(wsPackage.data, WSEvent::class.java)
        return wsEvent
    }
}