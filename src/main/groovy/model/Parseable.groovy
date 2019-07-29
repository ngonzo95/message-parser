package model

/**
 * This interface enables objects to define how jackson should parse them when they are in json
 */
trait Parseable {
    abstract static def parse(Map map)
}