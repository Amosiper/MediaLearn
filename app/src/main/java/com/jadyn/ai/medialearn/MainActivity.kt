package com.jadyn.ai.medialearn

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jadyn.ai.medialearn.camera.Camera2Activity
import com.jadyn.ai.medialearn.camera.CameraActivity
import com.jadyn.ai.medialearn.permissions.RxPermissions
import com.jadyn.ai.medialearn.utils.start
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subscribe = RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe {
                    RxPermissions(this).request(Manifest.permission.UPDATE_DEVICE_STATS).subscribe()
                }

        tv_go_camera.setOnClickListener {
            start<CameraActivity>()
        }

        tv_go_camera2.setOnClickListener {
            RxPermissions(this).request(
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    .doOnNext {
                        if (it) {
                            start<Camera2Activity>()
                        }
                    }
                    .subscribe()
        }
    }
}