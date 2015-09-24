//
//  ViewController.swift
//  Hex Clock
//
//  Created by Christopher Paterson on 27/07/2015.
//  Copyright © 2015 Christopher Paterson. All rights reserved.
//

import UIKit
import Foundation

class ViewController: UIViewController {
    
    @IBOutlet weak var textLabel: UILabel!
    
    var hexValue: String = ""{
        didSet {
            textLabel.text = "#" + hexValue
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view, typically from a nib.
        NSTimer.scheduledTimerWithTimeInterval(1.0, target: self, selector: Selector("getDateFromString"), userInfo: nil, repeats: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func preferredStatusBarStyle() -> UIStatusBarStyle {
        return UIStatusBarStyle.LightContent
    }
    
    func getDateFromString() {
        let date = NSDate()
        let calendar = NSCalendar.currentCalendar()
        let components = calendar.components([.Hour, .Minute, .Second], fromDate: date)
        
        formatDateForDisplay(components)
        createColorForBackground(components)
    }
    
    func formatDateForDisplay(components: NSDateComponents) {
        var formattedDate = ""
        
        let hour = String(components.hour)
        let minute = String(components.minute)
        let second = String(components.second)
        
        formattedDate += padIfNeeded(hour)
        formattedDate += padIfNeeded(minute)
        formattedDate += padIfNeeded(second)
        
        hexValue = formattedDate
    }
    
    func padIfNeeded(timeToPad: String) -> String {
        var time = timeToPad
        
        if(Int(time) <= 9) {
            time = "0" + time
        }
        
        return time
    }
    
    func createColorForBackground(components: NSDateComponents) {
        
//        backgroundColor = UIColor(red: CGFloat(components.hour),
//            green: CGFloat(components.minute),
//            blue: CGFloat( components.second),
//            alpha: CGFloat(1))
        
         view.backgroundColor = UIColor(red: CGFloat(components.hour)/255,
            green: CGFloat(components.minute)/255,
            blue: CGFloat( components.second)/255,
            alpha: 1)
    }


}

