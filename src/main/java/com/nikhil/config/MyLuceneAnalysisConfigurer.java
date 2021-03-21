package com.nikhil.config;

import org.apache.lucene.analysis.charfilter.HTMLStripCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

public class MyLuceneAnalysisConfigurer implements LuceneAnalysisConfigurer{
	@Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        context.analyzer( "customAnalyzer" ).custom() 
                .tokenizer( StandardTokenizerFactory.class )
                .charFilter( HTMLStripCharFilterFactory.class )
                .tokenFilter( LowerCaseFilterFactory.class ) 
                .tokenFilter( SnowballPorterFilterFactory.class ).param( "language", "English" ) 
                .tokenFilter( ASCIIFoldingFilterFactory.class );
	}
}
