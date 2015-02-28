package chandu0101.scalajs.location.mapper

import chandu0101.scalajs.facades.pouchdb.{ChangesOptions, PouchDB}

import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.Dynamic.{global => g}
import scalajs.js.Dynamic.{literal => json}



object App extends JSApp {
  var movementLayer : js.Dynamic = null
  @JSExport
  override def main(): Unit = {
    //  idiot why u using scalajs ? ( will create facade later busy atm :( )
    val map = g.L.map("map").setView(js.Array(42.36, -71.1), 10)
    g.L.tileLayer("https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png", 
    json("maxZoom" -> 18, "detectRetina" -> true,"id" -> "examples.map-20v6611k",
    "attribution" -> """Map data &copy;<a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a> """)
    ).addTo(map)
    g.L.Icon.Default.imagePath = "http://chandu0101.github.io/scalajs-location-mapper/images"
    movementLayer = g.L.geoJson().addTo(map)
    def updatingMovingLayer(change : js.Dynamic) = {
      if ( !change.doc._deleted.asInstanceOf[Boolean] && change.doc.`type`.toString == "Feature" ) {
        movementLayer.addData(change.doc)
        map.fitBounds(movementLayer.getBounds());
      }
    }
    // get data from DB and start listening
    val remoteStore = s"https://chandu0101.cloudant.com/locationtracker"
    val db = PouchDB.create(remoteStore)
    db.changes(ChangesOptions(include_docs = true,live = true))
      .onChange(updatingMovingLayer _)
  }
  
 
}
