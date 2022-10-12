package com.util.Config;

import com.util.Inventory.Model.Item;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class batchConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public FlatFileItemReader<Item> reader(){
        FlatFileItemReader<Item> reader=new FlatFileItemReader<Item>();
        reader.setResource(new ClassPathResource("sample_inventory.csv"));
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    private LineMapper<Item> getLineMapper() {

        DefaultLineMapper<Item> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"code","name","batch","stock","deal","free","mrp","rate","exp","company","supplier"});
        lineTokenizer.setIncludedFields(new int[]{0,1,2,3,4,5,6,7,8,9,10,11});

        BeanWrapperFieldSetMapper<Item> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Item.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;

    }

    @Bean
    public itemProcessor processor(){
        return new itemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Item> writer(){
        JdbcBatchItemWriter<Item> writer=new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Item>());
        writer.setSql("insert into item(id,code,name,batch,stock,deal,free,mrp,rate,exp,company,supplier) values(:id,:code,:name,:batch,:stock,:deal,:free,:mrp,:rate,:exp,:company,:supplier)");
        writer.setDataSource(this.dataSource);
        return writer;
    }

    @Bean
    public Job importItemJob(){
        return this.jobBuilderFactory.get("ITEM-IMPORT-JOB")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    private Step step1() {
        return this.stepBuilderFactory.get("step1")
                .<Item,Item>chunk(10)
                .reader(reader())
                .processor(processor())
                .build();
    }

}
