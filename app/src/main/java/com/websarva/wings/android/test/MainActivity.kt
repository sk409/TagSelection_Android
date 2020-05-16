package com.websarva.wings.android.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.*
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tag_list.*
import kotlinx.android.synthetic.main.fragment_tag_selection.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        val selectedTagLayoutManager = FlexboxLayoutManager(this)
        // 配置方向を指定
        selectedTagLayoutManager.flexDirection = FlexDirection.ROW
        // 折り返し方法を指定
        selectedTagLayoutManager.flexWrap = FlexWrap.WRAP
        // 主軸方向の揃え位置を指定
        selectedTagLayoutManager.justifyContent = JustifyContent.FLEX_START
        // 交差軸方向の揃え位置を指定
//        selectedTagLayoutManager.alignItems = AlignItems.STRETCH
        selectedTagRecyclerView.layoutManager = selectedTagLayoutManager
        val selectedTagRecyclerViewAdapter = SelectedTagRecyclerViewAdapter()
        selectedTagRecyclerView.adapter = selectedTagRecyclerViewAdapter

        tagDropDownButton.setOnClickListener {
            tagSelectionFragment.view?.let {
                it.visibility = if (it.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        val genderTags = mutableListOf(Tag("メンズ"), Tag("レディース"))
        val styleTags = mutableListOf(Tag("セミロング"), Tag("ミディアム"), Tag("ロング"), Tag("ショート"), Tag("ベリーショート"), Tag("マッシュ"))
        val allTags = arrayListOf(genderTags, styleTags).flatten().toMutableList()
        val tabs = arrayListOf(
            TagSelectionFragment.Tab("全て", allTags),
            TagSelectionFragment.Tab("性別", genderTags),
            TagSelectionFragment.Tab("スタイル", styleTags)
        )
        val tagListItemClickListener: (Tag) -> Unit = { tag ->
            selectedTagRecyclerViewAdapter.appendTag(tag.name)
        }
        val adapter = TagSelectionFragment.Adapter(supportFragmentManager, tabs, tagListItemClickListener)
        tabPager.adapter = adapter
        tabLayout.setupWithViewPager(tabPager)

        filterInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}
