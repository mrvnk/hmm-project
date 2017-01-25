
(use 'clojure.java.io)
(use '[clojure.string :only (split)])
(defn get_tokens [file]
  (with-open [wrtr (writer "C:/Users/Kenobi/IdeaProjects/CLPT/src/token_pos_tags.txt" :append true)]
    (with-open [rdr (reader file)]
      (def output
        (doseq [line (line-seq rdr)]
          (when (re-find #"^.*\<t id" line)
            (def line-to-write
              (str
                (nth (rest (split line #"\"") ) 4 ) ;; get token
                " "
                (nth (rest (split line #"\"") ) 6 )  ;; get pos tag
                "\n"
              ))
            ;;(print line-to-write)
            (conj output line-to-write)
            ;;(.write wrtr line-to-write)
          ))
      )
    )))

(get_tokens "/C:/Users/Kenobi/Ressourcen/TuebaDZ/9.0/corpora/t_890102_141.xml")


(def output)



(doseq [item (file-seq (file "/C:/Users/Kenobi/Ressourcen/TuebaDZ/9.0/corpora/"))]
  (when (.exists (file item))
    (get_tokens (.getPath item))
  )
)



(defn record-word-count [memo word]
  (assoc memo word (inc (memo word 0))))

(defn count-words [words-to-count]
  (reduce record-word-count {} words-to-count))


(count-words ["test" "test" "test"])
