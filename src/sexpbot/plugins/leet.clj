(ns sexpbot.plugins.leet
  (:use sexpbot.respond)
  )


(defn char-to-leet [c]
  (condp = c
    \e 3
    \a 4
    \b 8
    \g 9
    \i \!
    \l 1
    \o 0
    \q 9
    \s 5
    \t 7
    \z 2
    c))

(defn leetspeek [s]
  (->> s (#(.toLowerCase %)) (map char-to-leet) (apply str)))

(defplugin
  (:elite 
   "Takes words and replaces them with their leetspeak alternatives." 
   ["elite"] 
   [{:keys [irc channel args]}]
   (send-message irc channel 
		      (->> args 
			   (interpose " ")
			   (apply str)
			   (#(.toLowerCase %))
			   leetspeek))))