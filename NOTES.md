# Notes

Some refactoring I would like to do:

Technical:
- Create a value object for SKU rather than using String object
- Create abstract Product class and move common logic like adding promotions
- Use factory or factory method pattern to be able to create Promotions, may be give that responsibility to Product
- Use a mocking framework rather than creating StaticDiscountCalculator under test

Business:
- Would be nice to have some sort of order on Promotions may be a priority attribute
- If an item applicable for two promotions, we probably would like to apply only one
