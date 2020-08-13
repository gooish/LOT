java Oope2HT.java jokes_oldies.txt stop_words.txt < input_brain_check.txt > out1.txt
java Oope2HT.java jokes_oldies.txt stop_words.txt < input_print1.txt > out2.txt
java Oope2HT.java news_reuters.txt stop_words.txt < input_print2.txt > out3.txt


fc output_brain_check.txt out1.txt
fc output_print_1.txt out2.txt
fc output_print_2.txt out3.txt
