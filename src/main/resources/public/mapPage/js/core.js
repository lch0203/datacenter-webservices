var map;
var zoom = 6;
var markerClusterer;
var copymarkers;
var markerClickEvents;

function getParameter(param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);

    return query.substring(iStart, iEnd);
}

function resize() {
    var width = 0;
    width = getTotalWidth();
    if (width < 600) {
        width = 600;
    }

    jQuery("#app").width(width - 1);
    jQuery("#app").height(getTotalHeight() - 1);
}

function getTotalHeight() {
    if (jQuery.browser.msie) {
        return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
    }
    else {
        return self.innerHeight;
    }
}

function getTotalWidth() {
    if (jQuery.browser.msie) {
        return document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth : document.body.clientWidth;
    }
    else {
        return self.innerWidth;
    }
}

$(function () {
    window.onresize = resize;
    resize();
    loadMapAndControl();
    // var addr = "http://127.0.0.1:8080";
    var addr = "http://192.168.141.19:8080";
    var id = getParameter('ID');
    switch (id * 1) {
        case 1:
            addMarkers(addr + "/listschoolinfo/ssfx");
            break;
        case 2:
            addMarkers(addr + "/listschoolinfo/jxd");
            break;
        case 3:
            addMarkers(addr + "/listschoolinfo/ycy");
            break;
        case 4:
            addMarkers(addr + "/listschoolinfo/level2xy");
            break;
        default:
            break;
    }
});

function loadMapAndControl() {
    map = new TMap("map");
    map.centerAndZoom(new TLngLat(102.82862, 24.82396), zoom);
    map.enableHandleMouseScroll();
    var zoomConfig = {
        type: "TMAP_NAVIGATION_CONTROL_LARGE",
        anchor: "TMAP_ANCHOR_TOP_LEFT",
        offset: [0, 0],
        showZoomInfo: true
    };
    zoomControl = new TNavigationControl(zoomConfig);
    map.addControl(zoomControl);
    var overviewConfig = {
        anchor: "TMAP_ANCHOR_BOTTOM_RIGHT",
        size: new TSize(180, 120),
        isOpen: true
    };
    overviewMap = new TOverviewMapControl(overviewConfig);
    map.addControl(overviewMap);
    var scaleControl = new TScaleControl();
    map.addControl(scaleControl);
    var mapTypeControl = new TMapTypeControl({mapTypes: [TMAP_SATELLITE_MAP, TMAP_HYBRID_MAP, TMAP_NORMAL_MAP]});
    map.addControl(mapTypeControl);
    var searchConfig = {
        pageCapacity: 10,
        onSearchComplete: localSearchResult
    };
    localsearch = new TLocalSearch(map, searchConfig);
    localsearch.search("云南");
}


function setMapWindowSize() {
    $('#map').width($(window).width());
    $('#map').height($(window).height());
}


function localSearchResult(result) {
    area(result.getArea());
}

function area(obj) {
    if (obj) {
        var pointsArr = [];
        var points = obj.points;
        for (var i = 0; i < points.length; i++) {
            var regionLngLats = [];
            var regionArr = points[i].region.split(",");
            for (var m = 0; m < regionArr.length; m++) {
                var lnglatArr = regionArr[m].split(" ");
                var lnglat = new TLngLat(lnglatArr[0], lnglatArr[1]);
                regionLngLats.push(lnglat);
                pointsArr.push(lnglat);
            }

            var polygon = new TPolygon(regionLngLats, {
                strokeColor: "#ff5a00",
                fillColor: "blue",
                strokeWeight: 3,
                strokeOpacity: 1,
                fillOpacity: 0.05,
                strokeStyle: "solid"
            });
            map.addOverLay(polygon);
        }
    }
}

function addMarkers(jsonUrl) {
    if (markerClusterer) {
        var markers = markerClusterer.getMarkers();
        for (var i = 0; i < markers.length; i++) {
            markers[i].closeInfoWindow();
            copymarkers[i].closeInfoWindow();
            TEvent.removeListener(markerClicks[i]);
        }
        markerClusterer.clearMarkers();
    }
    markerClicks = [];
    copymarkers = [];
    $.getJSON(jsonUrl, function (data) {
        var markers = [];
        for (var i = 0; i < data.markers.length; i++) {
            var marker = new TMarker(new TLngLat(data.markers[i].longitude, data.markers[i].latitude));
            map.addOverLay(marker);
            marker.t = data.markers[i].school;
            marker.c = data.markers[i].infomation;
            (function () {
                var m = marker;
                markerclick = TEvent.addListener(m, "click", function (p) {
                    m.openInfoWinHtml(m.c);
                });
                copymarkers.push(m);
                markerClicks.push(markerclick);
            })();

            markers.push(marker);
        }
        var markerClustererConfig = {
            markers: markers,
            girdSize: 60,
            maxZoom: 10,
            minClusterSize: 2
        };
        markerClusterer = new TMarkerClusterer(map, markerClustererConfig);
    });
}