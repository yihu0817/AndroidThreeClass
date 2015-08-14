
package com.scxh.android.fragement.subclassfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxh.android.R;

public class ArticleFragment extends Fragment {
	private final static String ARG_POSITION = "position";
    private int mCurrentPosition = -1;

	static String[] Articles = {
			" 张三\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.",
			" 李四\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.",
			"王二\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia ",
			"麻子\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trus" };
    public static ArticleFragment newInstance(int position) {
    	ArticleFragment frag = new ArticleFragment();
		
		Bundle args = new Bundle();
		args.putInt(ARG_POSITION, position);
		frag.setArguments(args);
		
		return frag;
	}
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	mCurrentPosition = getArguments().getInt(ARG_POSITION);
    	
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.article_view, container, false);
        TextView  mArticleTxt = (TextView) v.findViewById(R.id.article);
        mArticleTxt.setText(Articles[mCurrentPosition]);
        
        mArticleTxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getFragmentManager()
				.beginTransaction()
				.replace(R.id.contanerFragments,MyListFragment.newInstance())
				.addToBackStack(null)
				.commit();
			}
		});
        
        return v;
    }

}